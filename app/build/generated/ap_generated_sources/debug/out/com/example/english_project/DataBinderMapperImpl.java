package com.example.english_project;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.english_project.databinding.FragmentAddingPageBindingImpl;
import com.example.english_project.databinding.FragmentDatabaseBindingImpl;
import com.example.english_project.databinding.FragmentFlashCardBindingImpl;
import com.example.english_project.databinding.FragmentMenuBindingImpl;
import com.example.english_project.databinding.FragmentModifStatsBindingImpl;
import com.example.english_project.databinding.FragmentSettingsBindingImpl;
import com.example.english_project.databinding.ItemTraductionBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRAGMENTADDINGPAGE = 1;

  private static final int LAYOUT_FRAGMENTDATABASE = 2;

  private static final int LAYOUT_FRAGMENTFLASHCARD = 3;

  private static final int LAYOUT_FRAGMENTMENU = 4;

  private static final int LAYOUT_FRAGMENTMODIFSTATS = 5;

  private static final int LAYOUT_FRAGMENTSETTINGS = 6;

  private static final int LAYOUT_ITEMTRADUCTION = 7;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(7);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.english_project.R.layout.fragment_adding_page, LAYOUT_FRAGMENTADDINGPAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.english_project.R.layout.fragment_database, LAYOUT_FRAGMENTDATABASE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.english_project.R.layout.fragment_flash_card, LAYOUT_FRAGMENTFLASHCARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.english_project.R.layout.fragment_menu, LAYOUT_FRAGMENTMENU);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.english_project.R.layout.fragment_modif_stats, LAYOUT_FRAGMENTMODIFSTATS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.english_project.R.layout.fragment_settings, LAYOUT_FRAGMENTSETTINGS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.english_project.R.layout.item_traduction, LAYOUT_ITEMTRADUCTION);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRAGMENTADDINGPAGE: {
          if ("layout/fragment_adding_page_0".equals(tag)) {
            return new FragmentAddingPageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_adding_page is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDATABASE: {
          if ("layout/fragment_database_0".equals(tag)) {
            return new FragmentDatabaseBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_database is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFLASHCARD: {
          if ("layout/fragment_flash_card_0".equals(tag)) {
            return new FragmentFlashCardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_flash_card is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMENU: {
          if ("layout/fragment_menu_0".equals(tag)) {
            return new FragmentMenuBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_menu is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMODIFSTATS: {
          if ("layout/fragment_modif_stats_0".equals(tag)) {
            return new FragmentModifStatsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_modif_stats is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSETTINGS: {
          if ("layout/fragment_settings_0".equals(tag)) {
            return new FragmentSettingsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_settings is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMTRADUCTION: {
          if ("layout/item_traduction_0".equals(tag)) {
            return new ItemTraductionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_traduction is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(7);

    static {
      sKeys.put("layout/fragment_adding_page_0", com.example.english_project.R.layout.fragment_adding_page);
      sKeys.put("layout/fragment_database_0", com.example.english_project.R.layout.fragment_database);
      sKeys.put("layout/fragment_flash_card_0", com.example.english_project.R.layout.fragment_flash_card);
      sKeys.put("layout/fragment_menu_0", com.example.english_project.R.layout.fragment_menu);
      sKeys.put("layout/fragment_modif_stats_0", com.example.english_project.R.layout.fragment_modif_stats);
      sKeys.put("layout/fragment_settings_0", com.example.english_project.R.layout.fragment_settings);
      sKeys.put("layout/item_traduction_0", com.example.english_project.R.layout.item_traduction);
    }
  }
}
