import java.io.File;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

public class HeartAnalysisJava {
    public void callPythonMethods() {
        Context ctx = Context.newBuilder()
        //.option("python.CoreHome", "/Users/vijaykumarab/Downloads/graalvm-ee-java11-21.3.0/Contents/Home/languages/python/lib-graalpython")
        //.option("python.StdLibHome", "/Users/vijaykumarab/Downloads/graalvm-ee-java11-21.3.0/Contents/Home/languages/python/lib-python/3")
        //.option("python.CAPI", "/Users/vijaykumarab/Downloads/graalvm-ee-java11-21.3.0/Contents/Home/languages/python/lib-graalpython")
        //.option("python.Executable", "/Users/vijaykumarab/AB-Home/blogs/graalvm-numpy/Code/ab_venv/bin/python")

        .allowAllAccess(true).build();
        try {
            File fibCal = new File("./heartAnalysis.py");
            ctx.eval(Source.newBuilder("python", fibCal).build());
            
            Value hearAnalysisFn = ctx.getBindings("python").getMember("heartAnalysis");

            Value heartAnalysisReport = hearAnalysisFn.execute();
            System.out.println("Average age of people who are getting level 3 and greater chest pain :" + heartAnalysisReport.toString());

        }   catch (Exception e) {
            System.out.println("Exception : " );
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        HeartAnalysisJava obj = new HeartAnalysisJava();
        obj.callPythonMethods();
    }
}
