public class Matrix{

	private int row, col;
	private double[][] theArray;

	//Notes:
	//Rows are defined by the 2nd Dimension

//	Matrix(double[][] values){	//Depricated, I suppose
//		this.theArray = values; 
//	}

	Matrix(double[] values){ //ONLY FOR CREATING A MATRIX X-by-1 from array X
		this.theArray = new double[values.length][1];
		for (int i = 0; i<values.length; i++){
			this.theArray[i][0] = values[i];
		}
	}

	Matrix(double[]...one){
		this.theArray = one;
	}

	void Scale(double value){
		for (int i = 0; i < this.theArray.length; i++){
			for (int j = 0; j < this.theArray[i].length; j++){
				this.theArray[i][j]*=value;
			}
		}
	}

	void reassign(double[][] val){
		this.theArray = val;
	}

	//Index starts at 0.

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

	private int lengthDouble(double[][] a){
		int cnt = 0;
		for (int i = 0; i < a.length; i++){
			for (int j = 0; j<a[i].length; j++){
			cnt++;
			}
		}
		return cnt;
	}

	private String printArray(double[] a){
		String temp = "";
		for (int i = 0; i <a.length; i++){
			temp+=a[i]+" ";
		}
		return temp;
	}
	
	//TODO 
	//Element Wise Addition
	//Element Wise Multiplication
	

	Matrix elementWiseAdd(Matrix B){
	
		double[][] newArray;	

		newArray = this.toArray();
		Matrix newMatrix = new Matrix(newArray);

		if (this.lengthDouble(this.theArray)==this.lengthDouble(B.theArray)){
			
			for (int i = 0; i<B.theArray.length; i++){
				for (int j = 0; j<B.theArray[i].length;j++){	
					newArray[i][j]+=B.theArray[i][j];
				}
			}
			newMatrix = new Matrix(newArray);

		}else{
			return null;
		}

		return newMatrix;

	}

	Matrix elementWiseSub(Matrix B){	//Subtract B from A, New = A-B
	
		double[][] newArray;	

		newArray = this.toArray();
		Matrix newMatrix = new Matrix(newArray);

		if (this.lengthDouble(this.theArray)==this.lengthDouble(B.theArray)){
			
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

	Matrix dotProduct(Matrix two){
		Matrix one = this;
		double[][] newArray;
		if((one.getColumn(0).length == two.getRow(0).length)||(one.getRow(0).length == two.getColumn(0).length)){
			if (one.getRow(0).length == two.getColumn(0).length){
				Matrix temp = two;
				two = one;
				one = temp;
//				System.out.println("Flipped Matrix list");
			}
			
		
			if (this.lengthDouble(one.toArray()) < this.lengthDouble(two.toArray())){
				newArray = one.toArray();
			}else{
				newArray = two.toArray();
			}

			double writeTo[][] = new double[newArray.length][newArray[0].length];

			for (int i = 0; i < newArray.length; i++){
				for (int j = 0; j < newArray[i].length; j++){ 
					//i,j synonymous with row, and column
					writeTo[i][j] = smallDP(one.getColumn(j),two.getRow(i));
//					System.out.println("Dot Products: col: "+j+" and row: "+ i  );
				}
			}	
			newArray = writeTo;
		}else{
			newArray = new double[0][0];
		}
			return new Matrix(newArray);
	}

	public String toString(){
		String temp = "";
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
		this.flip();
		return this;
	}

	public double[][] toArray(){
		return this.theArray;
	}

	public double[][] getArray(){
		return toArray(); //welp, now I don't have to worry about my shitty notation.
	}

}
