import {createSlice} from '@reduxjs/toolkit';

const initialState = {
    data: {},
    categories: [],
    selectedCategory: null,
    selectedLandmark: null,
    newestId: null,
    openSnackbar: false,
    errorMessage: '',
    successMessage: '',
    loading: false,
};

const adminSlice = createSlice({
    name: 'form',
    initialState,
    reducers: {
        setData(state, action) {
            state.data = action.payload;
        },
        setCategories(state, action) {
            state.categories = action.payload;
        },
        setNewestId(state, action) {
            state.newestId = action.payload;
        },
        setOpenSnackbar(state, action) {
            state.openSnackbar = action.payload;
        },
        setErrorMessage(state, action) {
            state.errorMessage = action.payload;
        },
        setSuccessMessage(state, action) {
            state.successMessage = action.payload;
        },
        closeSnackbar(state) {
            state.openSnackbar = false;
        },
        setSelectedCategory(state, action) {
            state.selectedCategory = action.payload;
        },
        setSelectedLandmark(state, action) {
            state.selectedLandmark = action.payload;
        },
        updateData: (state, action) => {
            state.data = {
                ...state.data,
                ...action.payload
            };
        },
        setLoading(state, action) {
            state.loading = action.payload;
        }
    },
});

export const {
    setData,
    setCategories,
    setNewestId,
    setOpenSnackbar,
    setErrorMessage,
    setSuccessMessage,
    closeSnackbar,
    setSelectedCategory,
    setSelectedLandmark,
    updateData,
    setLoading
} = adminSlice.actions;

export default adminSlice.reducer;
