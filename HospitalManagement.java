//Q29

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Doctor
{
	String name;
	String specialization;
	String visiting_day;

	//Constructor for initialising doctor objects
	Doctor(String name, String specialization, String visiting_day)
	{
		this.name = name;
		this.specialization = specialization;
		this.visiting_day = visiting_day;
	}

	public String toString()
	{
		return "\n"+ name+ "\t"+ specialization+ "\t\t"+ visiting_day+ "\n";
	}
}

//User defined exception handling
class DoctorNotAvailableException extends Exception 
{
	private int a;

	DoctorNotAvailableException(int a) 
	{
		this.a = a;
	}

	 public String toString() 
	 {
	 	return "DoctorNotAvailableException: No doctor available on given day";
	 }
}


class HospitalManagement
{
	//ArrayList for holding doctor details
	ArrayList<Doctor> d = new ArrayList<Doctor>();

	//Constructor
	HospitalManagement()
	{
		//Creating 10 doctor objects with hard coded values
		d.add(new Doctor("shakthi", "Cardiology", "Sunday"));
		d.add(new Doctor("aravind", "Onocology", "Monday"));
		d.add(new Doctor("shivani", "Neurology", "Sunday"));
		d.add(new Doctor("sharadha", "Surgical Gastroenterology", "Monday"));
		d.add(new Doctor("ganesh", "Medical Gastroenterology", "Tuesday"));
		d.add(new Doctor("revanth", "Obstetrics", "Wednesday"));
		d.add(new Doctor("manikandan", "Gynaecology", "Thursday"));
		d.add(new Doctor("ram", "Bone Marrow Transplant", "Friday"));
		d.add(new Doctor("yoghesh", "Gynaecology", "Tuesday"));
		d.add(new Doctor("shiv", "Bone Marrow Transplant", "Thursday"));
	}

	//Method for creating list of doctors available on a particular day
	ArrayList<Doctor> SearchDoctor(String day) throws DoctorNotAvailableException
	{
		HospitalManagement hm = new HospitalManagement();
		ArrayList<Doctor> d1 = new ArrayList<Doctor>();
		int count = 0;
		
		for(int i = 0; i < 10; i++)
		{
			if(day.equals(d.get(i).visiting_day))
			{
				d1.add(d.get(i));
				 count++;
			}

		}
		if(count == 0)
			throw new DoctorNotAvailableException(count);
		return d1;
	}

	//main()
	public static void main(String[] args)
	{
		HospitalManagement hm1 = new HospitalManagement();

		//Creating components of frame
		JFrame f = new JFrame("XYZ Hospital");
		JLabel l,l1;
		String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		JComboBox cb;
		JButton b,b1;
		JTextArea ta;

		//Frame size, layout and visibility 
		f.setSize(500,500);
		f.setLayout(null);
		f.setVisible(true);

		//X button for closing frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Initialising frame components and positioning using setBounds method
		l = new JLabel("Doctors Information");
		l.setBounds(550, 50, 250, 20);
		l1 = new JLabel("Visting day");
		l1.setBounds(50, 100, 250, 20);

		//Using ComboBox for drop down list of days
		cb = new JComboBox(days); 
        cb.setBounds(250, 100, 250, 20); 
		
        //Button for searching available doctor on a particular day selected in the drop down menu
		b = new JButton("Search Doctor");
		b.setBounds(50, 200, 150, 40);

		//Button for displaying all the doctors information 
		b1 = new JButton("Display Doctors");
		b1.setBounds(250, 200, 150, 40);

		//Text area for displaying the output when a button is clicked
		ta = new JTextArea(); 
        ta.setBounds(600, 100, 500, 400); 
        
        //Adding all the components to the frame
        f.add(l);
        f.add(l1);
        f.add(cb);
        f.add(b);
        f.add(b1);
        f.add(ta);

        //Event handling
        b.addActionListener(new ActionListener()
		{ 
			String s;

			//Method to handle Search Doctor button actions
			public void actionPerformed(ActionEvent e)
			{
				s = String.valueOf(cb.getSelectedItem());
				ta.setText("Details of Doctors avaliable on "+ s);
				ta.setText(ta.getText()+ "\n\nName"+ "\t"+ "Specialization"+ "\t\t"+ "Visiting Day\n");
				try
				{
					ArrayList<Doctor> d2 = new ArrayList<Doctor>(hm1.SearchDoctor(s));
					for(int j = 0; j < d2.size(); j++)
						ta.setText(ta.getText()+ d2.get(j));
				}
				catch(DoctorNotAvailableException ex)
				{
					ta.setText(ta.getText()+ "\n"+ ex+ "\n\n");
				}
			}
		});

		b1.addActionListener(new ActionListener() 
         { 
         	HospitalManagement hm2 = new HospitalManagement();

         	//Method to handle Display Doctor button actions
         	public void actionPerformed(ActionEvent e) 
         	{ 
         		ta.setText("Details of all Doctors");
                ta.setText(ta.getText()+ "\n\nName"+ "\t"+ "Specialization"+ "\t\t"+ "Visting day\n");
                for(int k = 0; k < 10; k++)
                	ta.setText(ta.getText()+ hm2.d.get(k));
            } 
        });
    }
}
