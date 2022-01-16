> The demo about the java annotation.   

#### **Alternative Ways of Registering a Processor**

```
in fact, the processor is called by the javac compiler.	
```



#####  using the javac compiler 

* compiled the processor and the annotation 

```
javac src/main/java/annotation/LogComment.java
javac src/main/java/annotation/LogComment.java

or just build the project.
```



* export the CLASSPATH temp

```
use the embeded terminal in IDEA.
$ echo $CLASSPATH

$ CLASSPATH=$CLASSPATH:<the classpath of the processor and the annotation>

$ export CLASSPATH

$ echo $CLASSPATH
```



* compile the source code that contains the annotation

```
$ javac -processor processor.SourceAnnotationProcessor src/main/java/model/Student.java

or

$ javac -XprintRounds -processor csrc/main/java/model/Student.java

to see the detailed info
```



##### use maven compile plugin

```
Here’s an example of adding annotation processor for the compiler plugin. You could also specify the directory to put generated sources into, using the generatedSourcesDirectory configuration parameter.

Note that the annotation processor class should already be compiled, for instance, imported from another jar in the build dependencies:
```

```
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                    <generatedSourcesDirectory>${project.build.directory}
                        /generated-sources/</generatedSourcesDirectory>
                    <annotationProcessors>
                        <annotationProcessor>
                            processor.SourceAnnotationProcessor
                        </annotationProcessor>
                    </annotationProcessors>
                </configuration>
            </plugin>
        </plugins>
    </build>
```



##### Add a Processor Jar to the Classpath

```
Instead of specifying the annotation processor in the compiler options, you may simply add a specially structured jar with the processor class to the classpath of the compiler.
```

```
to pick it up automatically, the compiler has to know the name of the processor class. So you have to specify it in the META-INF/services/javax.annotation.processing.Processor file as a fully qualified class name of the processor:
```

```java
processor.SourceAnnotationProcessor
```

```
//for multiple processor
```

```java
package1.Processor1
package2.Processor2
package3.Processor3
```







##### Use the Google sauto-service Library**

```
To generate the registration file automatically, you can use the @AutoService annotation from the Google's auto-service library, like this:
```

```java
@AutoService(Processor.class)
public SourceAnnotationProcessor extends AbstractProcessor {
    // …
}
```

 ```
This annotation is itself processed by the annotation processor from the auto-service library. This processor generates the META-INF/services/javax.annotation.processing.Processor file containing the SourceAnnotationProcessor class name.
 ```



