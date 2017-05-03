CKEDITOR.editorConfig = function(config) {
    //config.extraPlugins = 'fmath_formula';
    config.resize_enabled = false;
    config.toolbar = 'MyToolbar';
    config.toolbar_MyToolbar = [
            [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript',
                    'Superscript', 'TextColor', 'BGColor', '-', 'Cut', 'Copy',
                    'Paste', 'Link', 'Unlink' ],
            [ 'Undo', 'Redo', '-', 'JustifyLeft', 'JustifyCenter',
                    'JustifyRight', 'JustifyBlock' ],
            [ 'Image', 'Table', 'Smiley', 'SpecialChar', 'PageBreak',
                    'Styles', 'Format', 'Font', 'FontSize', 'Maximize' ] ];
};

/*
 CKEDITOR.editorConfig = function(config) {
    config.extraPlugins = 'fmath_formula';
    config.resize_enabled = false;
    config.toolbar = 'MyToolbar';
    config.toolbar_MyToolbar = [
            [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript',
                    'Superscript', 'TextColor', 'BGColor', '-', 'Cut', 'Copy',
                    'Paste', 'Link', 'Unlink' ],
            [ 'Undo', 'Redo', '-', 'JustifyLeft', 'JustifyCenter',
                    'JustifyRight', 'JustifyBlock' ],
            [ 'Image', 'Table', 'Smiley', 'SpecialChar', 'PageBreak',
                    'Styles', 'Format', 'Font', 'FontSize', 'Maximize',
                    'fmath_formula' ] ];
};

 * */
