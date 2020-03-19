package cn.sits.rjb.common.utils;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;

public class Editor {

    public static void main(String[] args) {
        Editor editor=new Editor();
        try {
            editor.docFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public  void docFile() throws Exception {
        InputStream bodyIs = new FileInputStream("f:\\2.html");
        InputStream cssIs2 = new FileInputStream("f:\\ueditor.css");
        InputStream cssIs1 = new FileInputStream("f:\\codemirror.css");
        String body = this.getContent(bodyIs);
        String css1 = this.getContent(cssIs1);
        String css2 = this.getContent(cssIs2);
        //拼一个标准的HTML格式文档
        String content = "<html><head><style>" + css1+"\\n" +css2 + "</style></head><body>" + body + "</body></html>";
        InputStream is = new ByteArrayInputStream(content.getBytes("GBK"));
        OutputStream os = new FileOutputStream("F:\\2.doc");
        this.inputStreamToWord(is, os);
    }


    /**
     * 把is写入到对应的word输出流os中
     * 不考虑异常的捕获，直接抛出
     * @param is
     * @param os
     * @throws IOException
     */
    private void inputStreamToWord(InputStream is, OutputStream os) throws IOException {

        POIFSFileSystem fs = new POIFSFileSystem();
        //对应于org.apache.poi.hdf.extractor.WordDocument
        fs.createDocument(is, "WordDocument");
        fs.writeFilesystem(os);
        os.close();
        is.close();
    }

    /**
     * 把输入流里面的内容以UTF-8编码当文本取出。
     * 不考虑异常，直接抛出
     * @param ises
     * @return
     * @throws IOException
     */
    private String getContent(InputStream... ises) throws IOException {
        if (ises != null) {
            StringBuilder result = new StringBuilder();
            BufferedReader br;
            String line;
            for (InputStream is : ises) {
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((line=br.readLine()) != null) {
                    result.append(line);
                }
            }
            return result.toString();
        }
        return null;
    }
}
