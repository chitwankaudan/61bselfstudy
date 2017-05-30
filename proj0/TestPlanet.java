public class TestPlanet {

	public static void testPlanetConstructors(Planet p, Planet q){
		double force = p.calcForceExertedBy(q);
		System.out.println("Force between these two planets is " + force);
	}

	public static void main(String[] args) {
		Planet earth = new Planet(1.0, 2.0, 3.0, 4.0, 5.0, "earth.gif");
		Planet neptune = new Planet(6.0, 7.0, 8.0, 9.0, 10.0, "neptune.gif");
        testPlanetConstructors(earth, neptune);
    }

}