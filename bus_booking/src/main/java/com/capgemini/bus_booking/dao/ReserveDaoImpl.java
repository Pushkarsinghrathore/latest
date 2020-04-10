package com.capgemini.bus_booking.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.bus_booking.bean.Bus;
import com.capgemini.bus_booking.bean.Reserve;

public class ReserveDaoImpl implements ReserveDao {

	ArrayList<Reserve> lreserve = new ArrayList<Reserve>();

	public ReserveDaoImpl() {
		super();
		lreserve.add(new Reserve(1, "11", 4, "04-04-2020", "04-04-2020", 2));
		lreserve.add(new Reserve(2, "22", 8, "05-04-2020", "05-04-2020", 1));
		lreserve.add(new Reserve(3, "22", 8, "08-04-2020", "08-04-2020", 5));
		lreserve.add(new Reserve(4, "33", 3, "03-04-2020", "03-04-2020", 4));
		lreserve.add(new Reserve(5, "44", 4, "04-04-2020", "04-04-2020", 5));
	}

	@Override
	public void addReserveDao(ArrayList<Reserve> lreserve) {
		this.lreserve = lreserve;
	}

	@Override
	public ArrayList<Reserve> getreserveList() {
		return lreserve;
	}

	@Override
	public Reserve findById(int id) {
		Reserve res = lreserve.stream().filter(p -> p.getId() == id).findAny().orElse(null);
		return res;
	}

	@Override
	public List<Integer> getSeatNumbersByBusAndDate(int busid, String date) {
		List<Reserve> res = lreserve.stream().filter(p -> p.getBusID() == busid && p.getDt().equals(date))
				.collect(Collectors.toList());

		List<Integer> seatNumbers = new ArrayList<Integer>();
		for (Reserve row : res) {
			seatNumbers.add(row.getSeat());
		}
		return seatNumbers;
	}

	@Override
	public int seatAvailabilityDao(int id, String date) {
		int seatoccupied = new ReserveDaoImpl().getSeatNumbersByBusAndDate(id, date).stream().reduce((a, b) -> a + b)
				.get();
		Bus totalSeat = new BusDaoImpl().getLbusList().stream().filter(p -> p.getId() == id).findAny().orElse(null);
		return totalSeat.getAvailablityCount() - seatoccupied;
	}

	public static void main(String[] args) {
		ReserveDaoImpl daoImpl = new ReserveDaoImpl();
		System.out.println(daoImpl.seatAvailabilityDao(4, "04-04-2020"));
	}

}
