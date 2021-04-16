package shopping_list.panel;

import javax.swing.SwingConstants;

import shopping.dto.Sales;
import shopping.exception.NotSelectedException;
import shopping.service.SalesService;

@SuppressWarnings("serial")
public class MainPanel extends AbstractCustomTablePanel<Sales> {
	public MainPanel() {
	}
	 
	private SalesService service;
	@Override
	public Sales getItem() {
		int row = table.getSelectedRow();
		int orderNo = (int) table.getValueAt(row, 1);
		
		if(row == -1) {
			throw new NotSelectedException();
		}
		return list.get(list.indexOf(new Sales(orderNo)));
	}
//	 date, cu_no, order_no, ID, cu_name, phone,	p_code, order_num, Total_Sales
	@Override
	public void initList() {
		list = service.showMainList();		
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"날짜", "주문번호", "아이디", "고객번호", "고객명", "연락처", "제품코드", "주문수량", "판매액"};
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6, 7, 8);
		setTableCellWidth(100, 250);	
	}
	public void setService(SalesService service) {
		this.service = service;
	}


	@Override
	protected Object[] toArray(Sales t) {
		return new Object[] 
				{t.getDate(), t.getOrderNo(), t.getCuNo().getId(),
				t.getCuNo(), t.getCuNo().getCuName(), t.getCuNo().getPhone(),
				t.getpCode(), t.getOrderNum(), t.getSaleAmount()};
	}

}