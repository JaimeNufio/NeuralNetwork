public class Perceptron{

	
	int[] outputSet;

	double learningRate;
	double[] weights;
	double[][] inputSet;

	Perceptron(double[][] inputSet, int[] outputSet, double learningRate){
		this.learningRate = learningRate;
		this.inputSet = inputSet;
		this.outputSet = outputSet;
		this.weights = new double[inputSet[0].length];
		this.setWeights();
	}

	Perceptron(int in, double learningRate){
		this.learningRate = learningRate;
		this.weights = new double[in];
//		System.out.println("Warning: no InputSet nor OutputSet initiated");
		this.setWeights();
	}

	public double guess(double[] input){
		double output = 0;
		for (int i = 0; i < weights.length; i++){
			output+=this.weights[i]*input[i];
		}
		return sigmoid(output);
	}	

	void train(double[] input, int expected){
		double error = expected-guess(input);
		for (int i = 0; i < weights.length; i++){
			this.weights[i]+=input[i]*error*this.learningRate;
		}
	}

	void trainAll(){
		if (this.inputSet == null || this.outputSet == null)
			return;	
		for (int i = 0; i<inputSet.length; i++){
			this.train(inputSet[i],outputSet[i]);
		}
	}
	
	private void setWeights(){ //randomly assign values for weights
		for (int i = 0; i<weights.length; i++){
			weights[i] = Math.random();			
		}
	}


	double[] getWeights(){
		return weights;
	}


	void printWeights(){
		for (int i = 0; i < weights.length; i++){
			System.out.println("Weight "+(i+1)+": "+weights[i]);
		}
	}

	private double sigmoid(double num){
		double n = 0;
	//	n = num > 0 ? 1 : 0;
			
		n = 1/(1+Math.exp(-1*num));
	//	System.out.println("Sigmoid Got:"+n);
		return n;
	}

}

