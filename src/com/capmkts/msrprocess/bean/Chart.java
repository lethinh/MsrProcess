package com.capmkts.msrprocess.bean;

import java.io.Serializable;  

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.CartesianChartModel;  
import org.primefaces.model.chart.ChartSeries;  

@ManagedBean
@RequestScoped
public class Chart implements Serializable {  
  
    private CartesianChartModel categoryModel;  
  
    public Chart() {  
        createCategoryModel();  
    }  
  
    public CartesianChartModel getCategoryModel() {  
        return categoryModel;  
    }  
  
    private void createCategoryModel() {  
        categoryModel = new CartesianChartModel();  
  
        ChartSeries commitNum = new ChartSeries();  
        commitNum.setLabel("Loan % to Committed Amt");  
  
        commitNum.set("0500", 62);  
        commitNum.set("0501", 34);  
        commitNum.set("0502", 36);  
        commitNum.set("0503", 27);  
        commitNum.set("0504", 55);  
        commitNum.set("0505", 79);  
        commitNum.set("0506", 62);  
        commitNum.set("0507", 20);  
        commitNum.set("0508", 90);  
        commitNum.set("0509", 85);  
        
        commitNum.set("0510", 62);  
        commitNum.set("0511", 34);  
        commitNum.set("0512", 36);  
        commitNum.set("0513", 27);  
        commitNum.set("0514", 55);  
        commitNum.set("0515", 79);  
  
        categoryModel.addSeries(commitNum);  
    }  
} 
