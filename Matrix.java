public class Matrix{

	private int row, col;
	private double[][] theArray;

	//Notes:
	//Rows are defined by the 2nd Dimension

	//MATRIX: MxN
	//N - i
	//M - j

	Matrix(double[] values){ //ONLY FOR CREATING A MATRIX X-by-1 from array X
		this.theArray = new double[values.length][1];
		for (int i = 0; i<values.length; i++){
			this.theArray[i][0] = values[i];
		}
	}

	Matrix(double[]...one){
		this.theArray = one;
	}

	void scale(double value){
		for (int i = 0; i < this.theArray.length; i++){
			for (int j = 0; j < this.theArray[i].length; j++){
				this.theArray[i][j]*=value;
			}
		}
	}

	void reassign(double[][] val){
		this.theArray = val;
	}

	double[] getColumn(int n){
		double[] tempArray = new double[this.theArray.length];
		for (int i = 0; i < this.theArray.length; i++){
			tempArray[i]=this.theArray[i][n];
		}
		//System.out.println("GOT: "+this.printArray(newArray));
		return tempArray;
	}

	double[] getRow(int n){
		return this.theArray[n]; //So simple its kinda funny lol
	} 

	private double smallDP(double[] one, double[] two){ //requires both arrays to be of the same size.
		double sum = 0;
//		System.out.println("Sum: "+sum);
		for (int i = 0; i < one.length; i++){
			sum+=(one[i]*two[i]);
//			System.out.println(+one[i]+"*"+two[i]);
		}	
		return sum;
	}

	private int length(){
		int cnt = 0;
		for (int i = 0; i < theArray.length; i++){
			for (int j = 0; j<theArray[i].length; j++){
			cnt++;
			}
		}
		return cnt;
	}

	void printArray(double[] a){
		String temp = "";
		for (int i = 0; i <a.length; i++){
			temp+=a[i]+" ";
		}
		System.out.println(temp);
	}
	
	Matrix elementWiseAdd(Matrix B){
	
		double[][] newArray;	

		newArray = this.toArray();
		Matrix newMatrix;

		if (this.length()==B.length() && this.theArray[0].length == B.theArray[0].length){
			
			for (int i = 0; i<B.theArray.length; i++){
				for (int j = 0; j<B.theArray[i].length;j++){	
					newArray[i][j]+=B.theArray[i][j];
				}
			}
			newMatrix  = new Matrix(newArray);

		}else{
			return null;
		}

		return newMatrix;

	}

	Matrix elementWiseSub(Matrix B){	//Subtract B from A, New = A-B
	
		double[][] newArray;	

		newArray = this.toArray();
		Matrix newMatrix;

		if (this.length()==B.length() && this.theArray[0].length == B.theArray[0].length){
			
			for (int i = 0; i<B.theArray.length; i++){
				for (int j = 0; j<B.theArray[i].length;j++){	
					newArray[i][j]-=B.theArray[i][j];
				}
			}
			newMatrix = new Matrix(newArray);
		}else{
			return null;
		}

		return newMatrix;

	}

	//For NeuralNetwork, init the Weights Matrix
	public void writeToColumn(int column, double[] a){
		for (int i = 0; i<a.length; i++){
			theArray[i][column] = a[i];			
		}
	}	

	//formerly, getRows()
	public int getNumRows(){
		return this.toArray().length;
	}

	//formerly, getColumns()
	public int getNumColumns(){
		return this.toArray()[0].length;
	}

	Matrix dotProduct(Matrix one){ //the host is alway the right

		Matrix two = this;
		double[][] temp = new double[one.getNumColumns()][two.getNumRows()];
	
		if (one.getNumColumns() == two.getNumRows()){
	////		System.out.println("we good");
			temp = new double[one.getNumRows()][two.getNumColumns()];
			//System.out.printf("%d X %d\n",temp.length,temp[0].length);
		//	System.out.printf("Row: %d Column: %d\n",temp.length,temp[0].length);
			for(int i = 0; i<temp.length; i++){
				for(int j = 0; j<temp[0].length; j++){
		//			System.out.printf("(%d,%d)\n",i,j);
					temp[i][j]+= smallDP(one.getRow(i),two.getColumn(j));	
				}
			}
	//		System.out.println("did math");
		}else{
			System.out.printf("invalid maths (%d =/= %d)",one.getNumColumns(),two.getNumRows());
		}
		return new Matrix(temp);
	}
	
	//error is expected - resulted
	Matrix massSub(Matrix sub){
		
		double[][] one = this.toArray(), two = sub.toArray(), temp = new double[0][0];

		if (one.length == two.length && two[0].length == one[0].length){
			System.out.println("Good");
			temp = new double [one.length][one[0].length];
			for (int i = 0; i<one.length;i++){
				for(int j = 0; j<one[0].length;j++){
					System.out.printf("%s - %s\n",one[i][j],two[i][j]);
					temp[i][j] = one[i][j] - two[i][j];
				}
			}
		}
		return new Matrix(temp);
	}

	public String toString(){
		String temp = this.theArray.length+" X "+this.theArray[0].length+"\n";
		for (int i = 0; i < this.theArray.length; i++){
			for (int j = 0; j < this.theArray[i].length; j++){
				temp+=this.theArray[i][j]+" ";
			}
			temp+="\n";
		}
		return temp;
	}

	public void totalWrite(double n){
		double[][] tempArray = this.toArray();
		for (int i = 0; i<tempArray.length; i++){
			for (int j = 0; j < tempArray[0].length; j++){
				tempArray[i][j] = n;
			}
		}
		this.reassign(tempArray);
	}

	void flip(){ //flips [x][y] to [y][x]
		double[][] flippy = this.toArray();
		double[][] newFlippy = new double[flippy[0].length][flippy.length];
		for(int i = 0; i < flippy.length; i++){
			for(int j = 0; j<flippy[i].length; j++){
				newFlippy[j][i] = flippy[i][j];
			}
		}
		this.theArray = newFlippy;
	}

	Matrix  getFlip(){
		double[][] flippy = this.toArray();
		double[][] newFlippy = new double[flippy[0].length][flippy.length];
		for(int i = 0; i < flippy.length; i++){
			for(int j = 0; j<flippy[i].length; j++){
				newFlippy[j][i] = flippy[i][j];
			}
		}
		Matrix temp = new Matrix(newFlippy);
		return temp;
	}

	public double[][] toArray(){
		return this.theArray;
	}

	public double[][] getArray(){
		return toArray(); //welp, now I don't have to worry about my shitty notation.
	}

}


