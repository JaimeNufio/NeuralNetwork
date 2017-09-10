public class test{

	public static void main(String[] g){
	
		double[] a = {1,2,3}, b = {3,4,5,7,6,6,5,3};

//		System.out.println(smallDP(a,b));

		a = b;

	}

        static double smallDP(double[] one, double[] two){ //requires both arrays to be of the same size.
                double sum = 0;
                for (int i = 0; i < one.length; i++){
                        sum+=(one[i]*two[i]);
                }
                return sum;
        }


}
