public class Perceptron{

	public double sumAttachedError = 0;

	double output;

	double learningRate;
	double[] weights;

	Perceptron(int numWeights, double learningRate){
		this.learningRate = learningRate;
		this.weights = new double[numWeights];
		this.setWeights();
	}

	private void setWeights(){ //randomly assign values for weights
		for (int i = 0; i<weights.length; i++){
			weights[i] = Math.random();			
		}
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

	public void setWeights(double[] newWeights){ //for the NeuralNetwork to set weights in swaths 
		this.weights = newWeights;
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
		return (double) 1/(1+Math.exp(-1*num));
	}

}

