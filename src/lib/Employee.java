package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	public class PersonalInfo {
		private String firstName, lastName, idNumber, address;
		private boolean isForeigner, gender;
		// constructor, getters
	}
	
	public class JoinDate {
		private int year, month, day;
		// constructor, getters
	}
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private boolean gender; //true = Laki-laki, false = Perempuan
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;
	private FamilyMember spouse;
	private List<FamilyMember> children;

	public class FamilyMember {
		private String name;
		private String idNumber;
	}
	
	
	public Employee(String employeeId, PersonalInfo info, JoinDate joinDate) {
		this.employeeId = employeeId;
		this.firstName = info.getFirstName();
		this.lastName = info.getLastName();
		this.idNumber = info.getIdNumber();
		this.address = info.getAddress();
		this.yearJoined = joinDate.getYear();
	}
	
	
	
	 private int getBaseSalary(int grade) {
		switch (grade) {
			case 1: return 3000000;
			case 2: return 5000000;
			case 3: return 7000000;
			default: return 0;
		}
	}
	
	public void setMonthlySalary(int grade) {
		int baseSalary = getBaseSalary(grade);
		this.monthlySalary = isForeigner ? (int)(baseSalary * 1.5) : baseSalary;
	}
	
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
