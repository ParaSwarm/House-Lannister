
                                FavoriteUserItemAdapter adapter = new FavoriteUserItemAdapter(((FavoriteUserItemAdapter)getListAdapter()).getContext(), R.layout.listview_favorites_item_row, favorites.toArray(new User[0]));
                                setListAdapter(adapter);
                                Toast.makeText(getActivity(), String.format("User %s removed.", selectedFavorite.getName()), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
                .setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }
                ).show();