public class AND{


	public static void main(String[] args){

		//Using y = x
		//1 if y > x	
		double[][] input = {{0,1},{1,0},{1,1},{0,0}};
		int[] out = {0,0,1,0};
		int count = 0;

		Perceptron p = new Perceptron(2,.8);

		while(!satisfied(input,out,p,.98,.1)){
			count++;
			for (char i = 0; i<input.length;i++){
				p.train(input[i],out[i]);
			}
		}

		System.out.println("Training Loops: "+count);
		System.out.println("\n\"And\" Logic Gate Result:");
		System.out.println("(1,0)");
		double[] x = {1,0};	
		System.out.println(p.guess(x));
		System.out.println("(0,1)");
		double[] w = {0,1};	
		System.out.println(p.guess(w));
		System.out.println("(0,0)");
		double[] y = {0,0};	
		System.out.println(p.guess(y));
		System.out.println("(1,1)");
		double[] z = {1,1};	
		System.out.println(p.guess(z));
		p.printWeights();
		p.printBias();


	}

	static 	boolean satisfied(double[][] inputSet, int[] outputSet, Perceptron p, double yesCertainty, double noCertainty){
		for (int i = 0; i<inputSet.length;i++){
			if (p.guess(inputSet[i]) < yesCertainty && outputSet[i] == 1){
				return false;
			}
			if (p.guess(inputSet[i]) > noCertainty && outputSet[i] == 0){
				return false;
			}
		}
		return true;
	}
}
