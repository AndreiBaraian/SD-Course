package presentation;

import java.lang.reflect.Field;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class GenericTableView {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void createColumns(TableView tableView, List<?> list, List<String> notDisplayedFields) {
		int columnCount = list.get(0).getClass().getDeclaredFields().length;
		int cnt = 0;
		for(int i = 0; i < columnCount; i++){
			int j = i - cnt;
			int k = i;
			if((int) notDisplayedFields.parallelStream().filter(r -> r.equals(list.get(0).getClass().getDeclaredFields()[k].getName())).count() == 0){
				TableColumn col = new TableColumn(list.get(0).getClass().getDeclaredFields()[i].getName());
				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
	                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
	                    return new SimpleStringProperty(param.getValue().get(j).toString());                        
	                }                    
	            });
				tableView.getColumns().add(col);
			}
			else
				cnt++;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void createRows(TableView tableView, List<?> list, List<String> notDisplayedFields) {
		ObservableList<ObservableList> data = FXCollections.observableArrayList();
		int columnCount = list.get(0).getClass().getDeclaredFields().length;
		for(Object obj : list) {
			ObservableList<String> row = FXCollections.observableArrayList();
			for(int i = 0; i < columnCount; i++){
				int j = i;
				if((int) notDisplayedFields.parallelStream().filter(r -> r.equals(list.get(0).getClass().getDeclaredFields()[j].getName())).count() == 0){
					Field field = obj.getClass().getDeclaredFields()[i];
					field.setAccessible(true);
					try {
						row.add(field.get(obj).toString());
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			data.add(row);
		}
		tableView.setItems(data);
	}

}
