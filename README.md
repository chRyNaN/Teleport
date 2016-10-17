# Teleport

## Easily transfer data between Android Components

Teleport allows you to easily send and receive data between different components or lifecycle events. Simply annotate your objects and beam to send and bind to retreive. 

To send the data:

```java
public class SendActivity extends AppCompatActivity {

    @Data("myStringKey")
    String myString;
    @Data("myObjectKey")
    CustomObject myObject;
    @Data // No parameter will result in the field name key (integer)
    Integer integer;
    
    public void startNextActivity() {
        Teleport.beam(this);
        
        startActivity(ReceiveActivity.class, this);
    }
    
}
``` 

To receive the data:

```java
public class ReceiveActivity extends AppCompatActivity {

    @Data("myStringKey")
    String myString;
    @Data("myObjectKey")
    CustomObject myObject;
    @Data // No parameter will result in the field name key (integer)
    Integer integer;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Teleport.bind(this);
    }
    
}
```

To eliminate verbosity, you can use the `@DataFields` annotation on the class containing the fields. The `@DataFields` annotation takes an array of String values which each represent both the name of the field and the key of the value.

```java
@DataFields({"field1", "field2", "field3"})
public class MainActivity extends AppCompatActivity {

    String field1;
    Integer field2;
    CustomObject field3;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Teleport.bind(this);
    }
}
```

Teleport can be used to handle storing data between lifecycle changes. 

```java
@Override
public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    
    Teleport.beam(this, outState);
}

@Override
public void onRestoreInstanceState(Bundle inState) {
    super.onRestoreInstanceState(inState);
    
    Teleport.bind(this, inState);
}
```
