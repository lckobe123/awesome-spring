/**
 * webpack 创建应用程序所有依赖的关系图(dependency graph)。图的起点被称之为入口起点(entry point)。
 * 
 * 入口起点告诉 webpack 从哪里开始，并根据依赖关系图确定需要打包的内容。
 * 可以将应用程序的入口起点认为是根上下文(contextual root) 或 app 第一个启动文件。
 * 
 * @author Ternence
 * @version 1.0
 */
const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require("path");
var webpack = require("webpack")

module.exports = {
    /**
     * 单入口文件,webpack会从这个文件开始将所有依赖文件打包
     */
    entry: "./entry.js",

    output: {
        path: path.resolve(__dirname, './build'),
        filename: "bundle.js"
    },

    devServer: {
        inline: true,
        port: 8082,
        proxy: {

        }
    },


    module: {
        loaders: [
            {
                //编译js和jsx
                test: /\.(js|jsx)?$/,
                exclude: /(node_modules|bower_components)/,
                loader: 'babel-loader',
                query: {
                    presets: ['es2015', 'react']
                }

            },
            {
                test: /\.css$/,
                exclude: /node_modules/,
                use: [
                    // 打包样式到HTML中引用
                    "style-loader",
                    // 让js代码可以使用import关键字引入样式
                    "css-loader"
                ]
            },
            {
                test: /\.(png|svg|jpg|gif)$/,
                exclude: /node_modules/,
                use: [
                    // 打包图片的
                    "file-loader"
                ]
            }
        ]
    },


    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        // 指定打包时候的入口html文件
        new HtmlWebpackPlugin({ template: './index.html' })
    ]

}