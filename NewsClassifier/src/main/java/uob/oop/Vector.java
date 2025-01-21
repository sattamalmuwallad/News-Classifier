package uob.oop;

public class Vector {
    private double[] doubElements;

    public Vector(double[] _elements) {
        //TODO Task 3.1 - 0.5 marks
        this.doubElements = _elements;
    }

    public double getElementatIndex(int _index) {
        //TODO Task 3.2 - 2 marks
        if (_index < 0 || doubElements.length <= _index)
            return -1;
        else
            return doubElements[_index];
    }

    public void setElementatIndex(double _value, int _index) {
        //TODO Task 3.3 - 2 marks
        if (_index < 0)
            doubElements[doubElements.length - 1] = _value;
        else if (_index >= doubElements.length)
            doubElements[doubElements.length - 1] = _value;
        else
            doubElements[_index] = _value;


    }

    public double[] getAllElements() {
        //TODO Task 3.4 - 0.5 marks
        return this.doubElements;
        //you need to modify the return value
    }

    public int getVectorSize() {
        //TODO Task 3.5 - 0.5 marks
        return this.doubElements.length; //you need to modify the return value
    }

    public Vector reSize(int _size) {
        //TODO Task 3.6 - 6 marks
        if (_size == doubElements.length || _size <= 0) {
            Vector obj = new Vector(doubElements);
            return obj;
        } else {
            double[] arr;
            if (_size < doubElements.length) {
                arr = new double[_size];
                int i = 0;
                while (i != arr.length) {
                    arr[i] = doubElements[i];
                    i++;
                }

            } else {
                arr = new double[_size];
                int i = 0;
                while (i != doubElements.length) {
                    arr[i] = doubElements[i];
                    i++;
                }
                i = doubElements.length;
                while (i != _size) {
                    arr[i] = -1;
                    i++;
                }
            }
            Vector obj = new Vector(arr);
            return obj;

        }
    }

    public Vector add(Vector _v) {
        //TODO Task 3.7 - 2 marks
        Vector vec = reSize(Math.max(getVectorSize(), _v.getVectorSize()));
        _v = _v.reSize(Math.max(getVectorSize(), _v.getVectorSize()));
        int i = 0;
        while (i != vec.getVectorSize()) {
            vec.doubElements[i] += _v.doubElements[i];
            i++;
        }
        return vec;
    }

    public Vector subtraction(Vector _v) {
        //TODO Task 3.8 - 2 marks
        Vector vec = reSize(Math.max(getVectorSize(), _v.getVectorSize()));
        _v = _v.reSize(Math.max(getVectorSize(), _v.getVectorSize()));
        int i = 0;
        while (i != vec.getVectorSize()) {
            vec.doubElements[i] -= _v.doubElements[i];
            i++;
        }
        return vec;
    }

    public double dotProduct(Vector _v) {
        //TODO Task 3.9 - 2 marks
        double sum = 0;
        Vector vec = reSize(Math.max(getVectorSize(), _v.getVectorSize()));
        _v = _v.reSize(Math.max(getVectorSize(), _v.getVectorSize()));
        int i = 0;
        while (i != vec.getVectorSize()) {
            sum += vec.doubElements[i] * _v.doubElements[i];
            i++;
        }
        return sum;
    }
    public double cosineSimilarity(Vector _v) {
        //TODO Task 3.10 - 6.5 marks
        double product = 0, vectorSquared = 0, _vSquared = 0;
        Vector vec = reSize(Math.max(getVectorSize(), _v.getVectorSize()));
        _v = _v.reSize(Math.max(getVectorSize(), _v.getVectorSize()));
        int i = 0;
        while (i != vec.getVectorSize()) {
            product += (vec.doubElements[i] * _v.doubElements[i]);
            vectorSquared += Math.pow(vec.doubElements[i], 2);
            _vSquared += Math.pow(_v.doubElements[i], 2);
            i++;
        }
        vectorSquared = Math.sqrt(vectorSquared);
        _vSquared = Math.sqrt(_vSquared);
        return product / (vectorSquared * _vSquared);
    }

    @Override
    public boolean equals(Object _obj) {
        Vector v = (Vector) _obj;
        boolean boolEquals = true;

        if (this.getVectorSize() != v.getVectorSize())
            return false;

        for (int i = 0; i < this.getVectorSize(); i++) {
            if (this.getElementatIndex(i) != v.getElementatIndex(i)) {
                boolEquals = false;
                break;
            }
        }
        return boolEquals;
    }

    @Override
    public String toString() {
        StringBuilder mySB = new StringBuilder();
        for (int i = 0; i < this.getVectorSize(); i++) {
            mySB.append(String.format("%.5f", doubElements[i])).append(",");
        }
        mySB.delete(mySB.length() - 1, mySB.length());
        return mySB.toString();
    }
}
