package com.gelisim.konumkayituygulamasi;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class ClusterMarkerItem implements ClusterItem
{
    private LatLng position;
    private String title;
    private String snippet;
    private BitmapDescriptor icon;
    public ClusterMarkerItem(LatLng pos,String tit ,String sni)
    {
        position = pos;
        title = tit;
        snippet = sni;
    }
    @Override
    public LatLng getPosition()
    {
        return position;
    }

    @Override
    public String getTitle()
    {
        return title;
    }

    @Override
    public String getSnippet()
    {
        return snippet;
    }

    public void setIcon(BitmapDescriptor mIcon) {
        icon = mIcon;
    }

    public BitmapDescriptor getIcon(){
        return icon;
    }
}
