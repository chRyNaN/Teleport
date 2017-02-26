package com.chrynan.teleport;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

/**
 * Created by ckeenan on 2/26/17. A class that handles generated a binding class for fields that
 * are annotated with the {@link Data} annotation. This class actually creates the {@link JavaFile}
 * representing the code to be generated via the {@link #create(TypeElement, List)} method.
 */
class DataBinderCreator {

    private static final String BEAM_METHOD_NAME = "beam";
    private static final String BIND_METHOD_NAME = "bind";
    private static final String BIND_CLASS_SUFFIX = "_TeleportationMachine";
    private static final String GENERATED_ANNOTATION_VALUE_FIELD = "value";

    private static final ClassName GENERATED_ANNOTATION_CLASS_NAME = ClassName.get(Generated.class);
    private static final ClassName STRING_CLASS_NAME = ClassName.get(String.class);
    private static final ClassName SERIALIZABLE_CLASS_NAME = ClassName.get(Serializable.class);
    private static final ClassName PARCELABLE_CLASS_NAME = ClassName.get("android.os", "Parcelable");
    private static final ClassName COLLECTION_CLASS_NAME = ClassName.get(Collection.class);

    static JavaFile create(final TypeElement containerElement, final List<DataField> dataFields) {
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(containerElement.getSimpleName().toString() + BIND_CLASS_SUFFIX)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addSuperinterface(Teleporter.class)
                .addAnnotation(AnnotationSpec.builder(GENERATED_ANNOTATION_CLASS_NAME)
                        .addMember(GENERATED_ANNOTATION_VALUE_FIELD, "$S", TeleportAnnotationProcessor.FULL_NAME)
                        .build());

        String packageName = containerElement.getQualifiedName().toString().substring(0, containerElement.getQualifiedName().toString().lastIndexOf("."));

        ClassName containerClassName = ClassName.get(containerElement);
        String containerFieldName = containerClassName.simpleName().substring(0, 1).toLowerCase() + containerClassName.simpleName().substring(1);

        classBuilder.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(containerClassName, containerFieldName)
                        .build())
                .addParameter(ParameterSpec.builder(PersistableMapWriter.CLASS_NAME, PersistableMapWriter.DEFAULT_FIELD_NAME)
                        .build())
                .build());

        PersistableMapWriter writer = new PersistableMapWriter(PersistableMapWriter.DEFAULT_FIELD_NAME, containerFieldName);

        classBuilder.addMethod(createBeamMethod(dataFields, writer));

        classBuilder.addMethod(createBindMethod(dataFields, writer));

        return JavaFile.builder(packageName, classBuilder.build()).build();
    }

    private static MethodSpec createBindMethod(final List<DataField> dataFields, final PersistableMapWriter writer) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(BIND_METHOD_NAME)
                .addModifiers(Modifier.PUBLIC);

        for (final DataField field : dataFields) {
            if (field.isBindable()) {
                TypeName typeName = field.getTypeName();

                if (typeName.isPrimitive() || typeName.isBoxedPrimitive()) {
                    typeName.unbox();

                    if (typeName.equals(TypeName.BYTE)) {
                        builder.addCode(writer.getByte(field.getKey(), field.getName()));
                    } else if (typeName.equals(TypeName.CHAR)) {
                        builder.addCode(writer.getCharacter(field.getKey(), field.getName()));
                    } else if (typeName.equals(TypeName.SHORT)) {
                        builder.addCode(writer.getShort(field.getKey(), field.getName()));
                    } else if (typeName.equals(TypeName.INT)) {
                        builder.addCode(writer.getInteger(field.getKey(), field.getName()));
                    } else if (typeName.equals(TypeName.LONG)) {
                        builder.addCode(writer.getLong(field.getKey(), field.getName()));
                    } else if (typeName.equals(TypeName.FLOAT)) {
                        builder.addCode(writer.getFloat(field.getKey(), field.getName()));
                    } else if (typeName.equals(TypeName.DOUBLE)) {
                        builder.addCode(writer.getDouble(field.getKey(), field.getName()));
                    } else if (typeName.equals(TypeName.BOOLEAN)) {
                        builder.addCode(writer.getBoolean(field.getKey(), field.getName()));
                    }
                } else {
                    boolean isSerializable = false;
                    boolean isParcelable = false;
                    boolean isCollection = false;

                    for (final TypeMirror mirror : field.getTypeElement().getInterfaces()) {
                        ClassName interfaceClassName = ClassName.get((TypeElement) ((DeclaredType) mirror).asElement());

                        if (interfaceClassName.equals(SERIALIZABLE_CLASS_NAME)) {
                            isSerializable = true;
                        } else if (interfaceClassName.equals(PARCELABLE_CLASS_NAME)) {
                            isParcelable = true;
                        } else if (interfaceClassName.equals(COLLECTION_CLASS_NAME)) {
                            isCollection = true;
                        }
                    }

                    if (isParcelable) {
                        builder.addCode(writer.getParcelable(field.getKey(), field.getName()));
                    } else if (isSerializable) {
                        builder.addCode(writer.getSerializable(field.getKey(), field.getName()));
                    } else if (isCollection) {
                        builder.addCode(writer.getCollection(field.getKey(), field.getName()));
                    } else {
                        if (typeName.equals(STRING_CLASS_NAME)) {
                            builder.addCode(writer.getString(field.getKey(), field.getName()));
                        } else if (field.getElement().asType() instanceof ArrayType) {
                            builder.addCode(writer.getArray(field.getKey(), field.getName()));
                        } else {
                            builder.addCode(writer.getObject(field.getKey(), field.getName()));
                        }
                    }
                }
            }
        }

        return builder.build();
    }

    private static MethodSpec createBeamMethod(final List<DataField> dataFields, final PersistableMapWriter writer) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(BEAM_METHOD_NAME)
                .addModifiers(Modifier.PUBLIC);

        for (final DataField field : dataFields) {
            if (field.isBeamable()) {
                builder.addCode(writer.put(field.getKey(), field.getName()));
            }
        }

        return builder.build();
    }
}
