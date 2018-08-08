public class Perceptron{

	public double sumAttachedError = 0;

	double bias = 0;
	double learningRate;
	double[] weights;

	Perceptron(int numWeights, double learningRate){
		this.learningRate = learningRate;
		this.weights = new double[numWeights+1];
//		this.setWeights();
	}

	private void setWeights(){ //randomly assign values for weights
		for (int i = 0; i<weights.length; i++){
			weights[i] = 0;// Math.random();			
		}
	}

	public double guess(double[] input){
		double output = 0;
		for (int i = 0; i < weights.length-1; i++){
			output+=this.weights[i]*input[i];
		}
		output+=weights[weights.length-1];
		return sigmoid(output);
	}	

	void train(double[] input, int expected){
		double error = expected-guess(input);
		
		/*
		System.out.println("Got: "+guess(input));
		System.out.println("Expected: "+expected);
		System.out.println("Error: "+error);
		*/

		for (int i = 0; i < weights.length-1; i++){
			this.weights[i]+=error*input[i]*learningRate;
		//	System.out.println("Weight Delta: "+((error/(input[i]+.1))*learningRate));
		}
		weights[weights.length-1]+=error*learningRate;
		//  System.out.println("Bias Delta: "+((error/(1))*learningRate));
	}

	public void setWeights(double[] newWeights){ //for the NeuralNetwork to set weights in swaths 
		for (int i = 0; i<weights.length-1; i++){
			weights[i]=newWeights[i];
		}
	}

	public void getError(double[] newWeights){ //for the NeuralNetwork to set weights in swaths 
		this.weights = newWeights;
	}

	double[] getWeights(){
		return weights;
	}

	void printWeights(){
		for (int i = 0; i < weights.length-1; i++){
			System.out.println("Weight "+(i+1)+": "+weights[i]);
		}
	}
	
	void printBias(){
		System.out.println("Bias: "+weights[weights.length-1]);	
	}

	private double sigmoid(double num){
		return (double) 1/(1+Math.exp(-num));
	}

}

