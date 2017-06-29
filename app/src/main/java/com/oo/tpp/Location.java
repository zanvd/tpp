package com.oo.tpp;

/**
 * Class which stores locations.
 * Every location consists of coordinates, poetry text and location images.
 * Coordinates are described as [latitude, longitude].
 *
 * Locations are stored in the following order:
 * 		- Piazza Unita D'Italia
 * 		- Molo San Carlo
 * 		- Il colle di San Giusto
 * 		- Punto Franco & Trzaska burja
 * 		- Cittavecchia
 * 		- Ponterosso
 * 		- Piazza Oberdan
 * 		- Giardino Pubblico
 */
public class Location {
	private double[][] coordinates = {
			{45.650106, 13.767817},
			{45.652605, 13.765500},
			{45.648145, 13.772070},
			{45.641021, 13.748276},
			{45.649177, 13.771725},
			{45.641048, 13.760923},
			{45.654261, 13.775756},
			{45.654371, 13.785728}
	};

	private Integer[][] images = {
			{R.drawable.i1_1, R.drawable.i1_2, R.drawable.i1_3, R.drawable.i1_4, R.drawable.i1_5},
			{R.drawable.i2_1, R.drawable.i2_2, R.drawable.i2_3, R.drawable.i2_4},
			{R.drawable.i3_1, R.drawable.i3_2},
			{R.drawable.i4_1, R.drawable.i4_2, R.drawable.i4_3, R.drawable.i4_4,
					R.drawable.i4_5, R.drawable.i4_6, R.drawable.i4_7, R.drawable.i4_8,
					R.drawable.i4_9, R.drawable.i4_10, R.drawable.i4_11, R.drawable.i4_12,
					R.drawable.i4_13},
			{R.drawable.i5_1, R.drawable.i5_2, R.drawable.i5_3},
			{R.drawable.i6_1, R.drawable.i6_2, R.drawable.i6_3, R.drawable.i6_4},
			{R.drawable.i7_1, R.drawable.i7_2},
			{R.drawable.i8_1, R.drawable.i8_2}
	};

	private String[][] poetries = {
			{},
			{"p2_1.txt", "p2_2.txt", "p2_3.txt", "p2_4.txt"},
			{"p3_1.txt", "p3_2.txt"},
			{"p4_1.txt", "p4_2.txt", "p4_3.txt"},
			{},{},{},{}
	};

	/**
	 * Retrieve locations index.
	 *
	 * @param lat double
	 * @param lon double
	 * @return int
	 */
	public int getLocationIndex (double lat, double lon) {
		for (int i = 0; i < coordinates.length; i++) {
			if (distance(lat, lon, coordinates[i][0], coordinates[i][1]) < 0.02)
				return i;
		}

		return -1;
	}

	/**
	 * Get images for given location's index.
	 *
	 * @param location int
	 * @return Integer[]
	 */
	public Integer[] getImages (int location) {
		return images[location];
	}

	/**
	 * Get poetry files names for given location's index.
	 *
	 * @param location int
	 * @return String []
	 */
	public String[] getPoetries (int location) {
		return poetries[location];
	}

	/**
	 *  Get distance between two geo locations.
	 *
	 * @param lat1 double
	 * @param lon1 double
	 * @param lat2 double
	 * @param lon2 double
	 * @return double
	 */
	private double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1))
				* Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1))
				* Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
}
