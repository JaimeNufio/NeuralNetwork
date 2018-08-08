public class experiment{


	public static void main(String[] args){

		//Using y = x
		//1 if y > x	
		double[][] input = {{0,1},{1,0},{1,1},{0,0}};
		double[][] inputa = {{0,2},{2,0},{2,2},{0,0}};
		int[] out = {1,1,1,0};
		int count = 0;

		Perceptron p = new Perceptron(2,.8);

		while(!satisfied(input,out,p)){
			count++;
			for (char i = 0; i<input.length;i++){
				p.train(input[i],out[i]);
			}
		}

/*
		double[] x = {1,0};	
		System.out.println(p.guess(x));
		double[] w = {0,1};	
		System.out.println(p.guess(w));
		double[] y = {0,0};	
		System.out.println(p.guess(y));
		double[] z = {1,1};	
		System.out.println(p.guess(z));
		p.printWeights();
		System.out.println("Training Loops: "+count);
*/

		Matrix m = new Matrix(input);
		System.out.println(m);
		Matrix n = new Matrix(inputa);
		n.scale(7);
		m.scale(2);
		System.out.println(m);
		System.out.println(n);
//		m.printArray(m.getRow(1));
		System.out.println(n.dotProduct(m));

		n=n.elementWiseSub(m);
		System.out.println(n);
	}

	static 	boolean satisfied(double[][] inputSet, int[] outputSet, Perceptron p){
		for (int i = 0; i<inputSet.length;i++){
			if (p.guess(inputSet[i]) < .9 && outputSet[i] == 1){
				return false;
			}
			if (p.guess(inputSet[i]) > .6 && outputSet[i] == 0){
				return false;
			}
		}
		return true;
	}
}
