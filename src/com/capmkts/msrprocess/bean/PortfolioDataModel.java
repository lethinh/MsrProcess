package com.capmkts.msrprocess.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

@ManagedBean
@RequestScoped
public class PortfolioDataModel extends ListDataModel<Portfolio> implements SelectableDataModel<Portfolio> {  

    public PortfolioDataModel() {
    }

    public PortfolioDataModel(List<Portfolio> data) {
        super(data);
    }
    
    @Override
    public Portfolio getRowData(String rowKey) {

        List<Portfolio> portfolios = (List<Portfolio>) getWrappedData();
        
        for(Portfolio portfolio : portfolios) {
            if(portfolio.getCMCCommitmentNumber().equals(rowKey))
                return portfolio;
        }
        
        return null;
    }

    @Override
    public Object getRowKey(Portfolio portfolio) {
        return portfolio.getCMCCommitmentNumber();
    }
}
                    
