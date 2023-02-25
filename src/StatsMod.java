
import org.apache.commons.math3.stat.inference.TTest;
import org.jfree.data.time.TimePeriodValues;

/**
 * Calculates the T-test between two time series.
 * 
 * @param ser1 the first time series
 * @param ser2 the second time series
 * @return the T-test statistic between the two time series
 */
public class StatsMod {
	public double timeSeriesTTest(TimePeriodValues ser1,TimePeriodValues ser2) {
		
		TTest tt = new TTest();
		
		double[] arr1 = toValArr(ser1);
		
		double[] arr2 = toValArr(ser2);
		
		return tt.tTest(arr1, arr2);
	}

	/**
	 * converts timeperiodvalues to array of doubles which contains the values ordered from first 
	 * added to last added.
	 * 
	 * @param tpv the TimePeriodValues to be converted
	 * @return an array of timePeriodValues as doubles
	 */
	private double[] toValArr(TimePeriodValues tpv) {
		int j = tpv.getItemCount();
		double[] ret = new double[j];
		for(int i = 0; i<j;i++) {
			ret[i]=tpv.getValue(i).doubleValue();
		}
		return ret;
	}
}
