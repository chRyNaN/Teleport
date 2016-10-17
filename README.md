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
