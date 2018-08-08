public class experiment{


	public static void main(String[] args){

		//Using y = x
		//1 if y > x	
		
		double[] input = new double[999];
		NeuralNetwork n = new NeuralNetwork(999,100,10,.7);
		n.guess(input);


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
