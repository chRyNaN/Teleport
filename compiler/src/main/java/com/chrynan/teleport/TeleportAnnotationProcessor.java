package com.chrynan.teleport;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.squareup.javapoet.JavaFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Created by ckeenan on 2/26/17. An annotation processor that looks for {@link Data} annotated
 * fields and will create a binding class that can be used to persist and retrieve values from and
 * for these fields.
 */
@AutoService(Processor.class)
public class TeleportAnnotationProcessor extends AbstractProcessor {

    static final String FULL_NAME = "com.chrynan.teleport.TeleportAnnotationProcessor";

    private Filer filer;
    private Messager messager;

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return ImmutableSet.of(Data.class.getCanonicalName());
    }

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        filer = env.getFiler();
        messager = env.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        final Map<TypeElement, List<DataField>> dataFieldMap = new HashMap<>();

        for (final Element element : roundEnvironment.getElementsAnnotatedWith(Data.class)) {
            TypeElement containingClass = (TypeElement) element.getEnclosingElement();
            DataField field = new DataField(element);

            List<DataField> fields = dataFieldMap.get(containingClass);

            if (fields == null) {
                fields = new ArrayList<>();
            }

            fields.add(field);

            dataFieldMap.put(containingClass, fields);
        }

        for (Map.Entry<TypeElement, List<DataField>> entry : dataFieldMap.entrySet()) {
            try {
                JavaFile file = DataBinderCreator.create(entry.getKey(), entry.getValue());
                file.writeTo(filer);
            } catch (IOException e) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Error generating Data field binding class. " +
                        "There was an issue writing the file out to the Filer. Message = " + e.getMessage());
            }
        }

        return false;
    }
}
