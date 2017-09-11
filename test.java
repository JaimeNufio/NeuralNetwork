public class test{

	public static void main(String[] g){
	
		//double[] a = {1,2,3}, b = {3,4,5,7,6,6,5,3};

//		System.out.println(smallDP(a,b));

		//a = b;

		double[] te = {1,2,3};
		Matrix t = new Matrix(te);
	
		double[] tes = {1,2,3};
		Matrix y = new Matrix(tes);

		y.flip();
		System.out.print(t+"Size x: "+t.toArray().length+"\n");
		t.flip();
		System.out.print(t+"Size x: "+t.toArray().length);
	//	System.out.print(t.dotProduct(y));

	}

        static double smallDP(double[] one, double[] two){ //requires both arrays to be of the same size.
                double sum = 0;
                for (int i = 0; i < one.length; i++){
                        sum+=(one[i]*two[i]);
                }
                return sum;
        }


}
