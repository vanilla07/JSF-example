package projectx.hotes.aloxe.controller;

import java.util.List;  
import javax.faces.model.ListDataModel;    
import org.primefaces.model.SelectableDataModel;  

import projectx.hotes.aloxe.model.Reservation;
  
public class ResDataModel extends ListDataModel<Reservation> implements SelectableDataModel<Reservation> {    
  
    public ResDataModel() {  
    }  
  
    public ResDataModel(List<Reservation> data) {  
        super(data);  
    }  
      
    @Override  
    public Reservation getRowData(String rowKey) {  
        @SuppressWarnings("unchecked")
		List<Reservation> resas = (List<Reservation>) getWrappedData();  
          
        for(Reservation res : resas) {  
            if(res.getPk().equals(rowKey))  
                return res;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Reservation res) {  
        return res.getPk();  
    }  
}  
  
