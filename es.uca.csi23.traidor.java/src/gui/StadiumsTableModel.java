package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import data.Stadium;

public class StadiumsTableModel extends AbstractTableModel {
	
	private final List<Stadium> _aData;
	
	public StadiumsTableModel(List<Stadium> aData){ _aData = aData; }

	@Override
	public int getRowCount() { return _aData.size(); }

	@Override
	public int getColumnCount() { return 1; }

	@Override
	public Object getValueAt(int iRow, int iColumn) {
		switch(iColumn) {
			case 0: return _aData.get(iRow).getName();
			default: throw new IllegalArgumentException("El número de columnas " + iColumn + " no puede ser procesado.");
		}
	}
	
	public Stadium getData(int iRow) {
		return _aData.get(iRow);
	}

}
