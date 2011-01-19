package com.pyxis.nikoniko.controller.transfer;

import java.util.List;

public class NikoCale {
    private List<RowData> rows;
    
    public NikoCale(List<RowData> rows) {
	this.rows = rows;
    }

    public List<RowData> getRows() {
        return rows;
    }
}
