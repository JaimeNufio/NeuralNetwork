public class test{

	public static void main(String[] g){
		NeuralNetwork nn = new NeuralNetwork(3,20,5,0.25);

		double[][] in = {{0,0,0},{0,0,1},{0,1,0},{0,1,1},{1,0,0},{1,0,1},{1,1,1}};
		double[][] expected = {{0},{1},{2},{3},{4},{5},{6},{7}};


		double[][] a = {{1,2,3},{4,5,6}};
	       	double[][] b  = {{1,4},{2,5},{3,6}};

		Matrix one = new Matrix(a);
		Matrix two = new Matrix(b);

		nn.guess(new double[]{1,5,6});
		

//		for (int i = 0; i<in.length; i++){
//			nn.train(in[i],expected[i]);
//		}
  //      }

	}
}
