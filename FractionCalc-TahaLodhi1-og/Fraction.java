
public class Fraction {

        private int numerator;
        private int denominator;
          
        Fraction(int n, int d){
            numerator = n;
            denominator = d;
        }

        public void updateFraction(int n, int d){
            numerator = n;
            denominator = d;
        }

        public int getDenominator() {
            return denominator;
        }

        public int getNumerator() {
            return numerator;
        }
};
