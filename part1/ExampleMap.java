import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class ExampleMap
{
   public static List<String> highEnrollmentStudents (Map<String, List<Course>> courseListsByStudentName, int unitThreshold)
   {
      List<String> overEnrolledStudents = new LinkedList<>();

      for (String key : courseListsByStudentName.keySet())
      {
         int sum = 0;
         for (Course course : courseListsByStudentName.get(key))
         {
            sum += course.getNumUnits();
         }
         if (sum > unitThreshold)
         {
            overEnrolledStudents.add(key);
         }
      }
      /*
         Build a list of the names of students currently enrolled
         in a number of units strictly greater than the unitThreshold.
      */

      return overEnrolledStudents;      
   }
}
