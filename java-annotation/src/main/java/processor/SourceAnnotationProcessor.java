package processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @program: java-annotation
 * @description: test
 * @author: xxx@gmail.com
 * @create: 2022-01-15 20:33
 **/
@SupportedAnnotationTypes("annotation.LogComment")
public class SourceAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Map<String, Set<? extends Element>> annotationMap = new HashMap<String, Set<? extends Element>>();
        for(TypeElement t: annotations){
                Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(t);
            annotationMap.put(t.getSimpleName().toString(), annotatedElements);

        }
        try {
            FileObject jf = processingEnv.getFiler()
                    .createResource(StandardLocation.CLASS_OUTPUT,"","test.properties");
            try (PrintWriter out = new PrintWriter(jf.openWriter())) {
                for(Map.Entry entry: annotationMap.entrySet()){
                    Set<? extends Element> set = (Set<? extends Element>) entry.getValue();
                    out.println(entry.getKey()+ ":");
                    for(Element element: set){
                        out.println(element.getSimpleName().toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}