package com.fragmentexample.lab212;


        import android.app.Fragment;
        import android.app.FragmentTransaction;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;


public class Fragment2 extends Fragment  {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment2, null);
    }

    public interface OnSelectedButtonListener {
        void onButtonSelected(int buttonIndex);
    }

}