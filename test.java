public class test{

	public static void main(String[] g){
//		NeuralNetwork nn = new NeuralNetwork(3,20,3,0.25);

		double[][] in = {{50},{30},{40},{70},{80},{90},{0},{2},{15},{-50},{-70},{17}};
		int[] out = {1,1,1,1,1,1,0,0,0,0,0,0};

		double[][] a = {{1,2,3},{4,5,6}};
	  double[][] b  = {{1,4},{2,5},{3,6}};

		Matrix one = new Matrix(a);
		Matrix two = new Matrix(b);

		double lr = .05; //universal learning rate
		Perceptron test = new Perceptron(1,lr);

		double[] val = {10};
		for (int i = 0; i<out.length;i++){
			test.printWeights();
			test.train(in[i],out[i]);
		}
		System.out.println(test.guess(val));
		//System.out.printf("%d returns %d",val[0],test.guess(val));
//		nn.guess(new double[]{1,5,6},true);
//		nn.train(new double[]{1,2,3},new double[]{2,4,5});
		

//		for (int i = 0; i<in.length; i++){
//			nn.train(in[i],expected[i]);
//		}
  //      }

	}
}
