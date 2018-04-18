package mercari.com.example.lily.common.listener;

import org.json.JSONArray;


import mercari.com.example.lily.common.constant.EnumConstant.Request_Status;


public interface CCDataListener {


	public interface CCDataSingleListener extends CCDataListener {

		public void onRequstDataComplete(Request_Status status, Object data);

	}

	public interface CCDataJsonListListener extends CCDataListener {

		public void onRequstDataComplete(Request_Status status, JSONArray datas);

	}

}
