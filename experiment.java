public class experiment{


	public static void main(String[] args){

		//Using y = x
		//1 if y > x	
		
		double[] input = new double[999];
		double[] errorT = {1,2,3,4,5,6,7};
		double[] targetT= {1,2,3,4,5};

		NeuralNetwork n = new NeuralNetwork(5,3,2,.7);
		n.guess(targetT);
	//	System.out.println(n.train(errorT,targetT));


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
