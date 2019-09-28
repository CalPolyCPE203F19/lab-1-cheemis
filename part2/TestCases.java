import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TestCases
{
   public static final double DELTA = 0.00001;

   /*
    * This test is just to get you started.
    */
   @Test
   public void testGetX1()
   {
      assertEquals(1.0, new Point(1.0, 2.0).getX(), DELTA);
   }

   @Test
   public void testGetX2()
   {
      assertEquals(5.0, new Point(5.0, 5.0).getX(), DELTA);
   }

   @Test
   public void testGetX3()
   {
      assertEquals(-3.0, new Point(-3.0, 3.0).getX(), DELTA);
   }

   @Test
   public void testGetY1()
   {
      assertEquals(2.0, new Point(1.0, 2.0).getY(), DELTA);
   }

   @Test
   public void testGetY2()
   {
      assertEquals(5.0, new Point(5.0, 5.0).getY(), DELTA);
   }

   @Test
   public void testGetY3()
   {
      assertEquals(-3.0, new Point(3.0, -3.0).getY(), DELTA);
   }

   @Test
   public void testGetRadius1()
   {
      assertEquals(5.0, new Point(3.0, 4.0).getRadius(), DELTA);
   }

   @Test
   public void testGetRadius2()
   {
      assertEquals(Math.sqrt(2), new Point(1, 1).getRadius(), DELTA);
   }

   @Test
   public void testGetRadius3()
   {
      assertEquals(5.65685424949, new Point(-4.0, -4.0).getRadius(), DELTA);
   }

   @Test
   public void testGetAngle1()
   {
      assertEquals(Math.PI/2, new Point(0.0, 1.0).getAngle(), DELTA);
   }

   @Test
   public void testGetAngle2()
   {
      assertEquals(0.0, new Point(1.0, 0.0).getAngle(), DELTA);
   }

   @Test
   public void testGetAngle3()
   {
      assertEquals(-2.35619, new Point(-4.0, -4.0).getAngle(), DELTA);
   }

   @Test
   public void testGetAngle4()
   {
      assertEquals(-0.785398, new Point(4.0, -4.0).getAngle(), DELTA);
   }

   @Test
   public void rotate901()
   {
      Point test1 = new Point(0.0, 1.0);
      Point test2 = new Point(1.0, 0.0).rotate90();

      assertEquals(test1.getX(), test2.getX(), DELTA);
      assertEquals(test1.getY(), test2.getY(), DELTA);
   }

   @Test
   public void rotate902()
   {
      Point test1 = new Point(-2.0, -2.0);
      Point test2 = new Point(-2.0, 2.0).rotate90();

      assertEquals(test1.getX(), test2.getX(), DELTA);
      assertEquals(test1.getY(), test2.getY(), DELTA);
   }

   @Test
   public void rotate903()
   {
      Point test1 = new Point(-4.0, 3.0);
      Point test2 = new Point(3.0, 4.0).rotate90();

      assertEquals(test1.getX(), test2.getX(), DELTA);
      assertEquals(test1.getY(), test2.getY(), DELTA);
   }

   /*
    * The tests below here are to verify the basic requirements regarding
    * the "design" of your class.  These are to remain unchanged.
    */

   @Test
   public void testImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getX",
         "getY",
         "getRadius",
         "getAngle",
         "rotate90"
         );

      final List<Class> expectedMethodReturns = Arrays.asList(
         double.class,
         double.class,
         double.class,
         double.class,
         Point.class
         );

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0],
         new Class[0],
         new Class[0],
         new Class[0],
         new Class[0]
         );

      verifyImplSpecifics(Point.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, Point.class.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertTrue("Unexpected number of public methods",
         expectedMethodNames.size()+1 >= publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
