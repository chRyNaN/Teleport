package com.chrynan.teleport;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;

/**
 * Created by ckeenan on 2/26/17. An object representation of a field annotated
 * with the {@link Data} annotation.
 */
class DataField {

    private final Element element;
    private final TypeElement typeElement;
    private final TypeName typeName;
    private final String fieldName;
    private final String key;
    private final boolean beam;
    private final boolean bind;

    DataField(Element element) {
        if (element == null || element.getAnnotation(Data.class) == null || element.getKind() != ElementKind.FIELD || element.getModifiers().contains(Modifier.PRIVATE)) {
            throw new IllegalArgumentException("Element parameter in DataField constructor must represent a non-private field annotated with the Data annotation." +
                    "Make sure that the field with the Data annotation is not private.");
        }

        this.element = element;
        this.typeElement = element instanceof TypeElement ? (TypeElement) element : (TypeElement) ((DeclaredType) element.asType()).asElement();
        this.typeName = element instanceof TypeElement ? ClassName.get((TypeElement) element) : TypeName.get(element.asType());
        this.fieldName = element.getSimpleName().toString();

        Data data = element.getAnnotation(Data.class);

        this.key = data.value();
        this.beam = data.beam();
        this.bind = data.bind();
    }

    Element getElement() {
        return element;
    }

    TypeElement getTypeElement() {
        return typeElement;
    }

    TypeName getTypeName() {
        return typeName;
    }

    String getName() {
        return fieldName;
    }

    String getKey() {
        return key;
    }

    boolean isBeamable() {
        return beam;
    }

    boolean isBindable() {
        return bind;
    }
}
