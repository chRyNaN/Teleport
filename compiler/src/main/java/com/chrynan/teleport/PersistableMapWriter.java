package com.chrynan.teleport;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;

/**
 * Created by ckeenan on 2/26/17. A convenience class used to obtain
 * {@link com.squareup.javapoet.CodeBlock} representations of calls to the PersistableMap
 * interface's method calls. This class provides type safety rather than manipulating Strings
 * directly in the processor or the creator class.
 */
class PersistableMapWriter {

    static ClassName CLASS_NAME = ClassName.get("com.chrynan.teleport.map", "PersistableMap");
    static String DEFAULT_FIELD_NAME = CLASS_NAME.simpleName().substring(0, 1).toLowerCase() + CLASS_NAME.simpleName().substring(1);

    private final String persistableMapFieldName;
    private final String containingClassFieldName;

    PersistableMapWriter(String persistableMapFieldName, String containingClassFieldName) {
        this.persistableMapFieldName = persistableMapFieldName;
        this.containingClassFieldName = containingClassFieldName;
    }

    CodeBlock put(final String key, final String fieldName) {
        return CodeBlock.of("$L.put($L, $L.$L);", persistableMapFieldName, key, containingClassFieldName, fieldName);
    }

    CodeBlock getByte(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getByte($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getShort(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getShort($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getInteger(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getInteger($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getLong(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getLong($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getFloat(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getFloat($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getDouble(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getDouble($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getBoolean(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getBoolean($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getCharacter(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getCharacter($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getString(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getString($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getSerializable(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getSerializable($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getParcelable(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getParcelable($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getObject(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getObject($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getCollection(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getCollection($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }

    CodeBlock getArray(final String key, final String fieldName) {
        return CodeBlock.of("$L.$L = $L.getArray($L);", containingClassFieldName, fieldName, persistableMapFieldName, key);
    }
}
