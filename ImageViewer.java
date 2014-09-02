package learnSwing;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

public class ImageViewer {

	final int PREVIEW_SIZE=100;
	JFrame jf=new JFrame("简单图片查看器");
	JMenuBar menuBar=new JMenuBar();
//	用于显示图片
	JLabel label=new JLabel();
//	以当前路径创造文件选择器
	JFileChooser chooser=new JFileChooser(".");
	JLabel accessory=new JLabel();
//	定义文件过滤器
	ExtensionFilter filter=new ExtensionFilter();
	public void init()
	{
//		创建一个filter
		filter.addExtension("jpg");
		filter.addExtension("jpeg");
		filter.addExtension("gif");
		filter.addExtension("png");
		filter.setDescription("图片文件");
		chooser.addChoosableFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileView(new FileIconViewer(filter));
		chooser.setAccessory(accessory);
		accessory.setPreferredSize(new Dimension(PREVIEW_SIZE,PREVIEW_SIZE));
		accessory.setBorder(BorderFactory.createEtchedBorder());
		chooser.addPropertyChangeListener(new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if(event.getPropertyName()==JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
				{
					File f=(File)event.getNewValue();
					if(f==null)
					{
						accessory.setIcon(null);
						return;
					}
					ImageIcon icon=new ImageIcon(f.getPath());
					if(icon.getIconWidth()>PREVIEW_SIZE)
					{
						icon=new ImageIcon(icon.getImage().getScaledInstance(PREVIEW_SIZE, -1, Image.SCALE_DEFAULT));
					}
					accessory.setIcon(icon);
				}
				
			}
			
		});
		JMenu menu=new JMenu("file");
		menuBar.add(menu);
		JMenuItem openItem=new JMenuItem("open");
		menu.add(openItem);
		openItem.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.setCurrentDirectory(new File("."));
				int result=chooser.showDialog(jf, "open file");
				if(result==JFileChooser.APPROVE_OPTION)
				{
					String name=chooser.getSelectedFile().getPath();
					label.setIcon(new ImageIcon(name));
				}
				
			}
			
		});
		JMenuItem exitItem=new JMenuItem("exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
		jf.setJMenuBar(menuBar);
		jf.add(new JScrollPane(label));
		jf.pack();
		jf.setVisible(true);
	}
	class ExtensionFilter extends FileFilter
	{
		private String description;
		private ArrayList<String> extensions=new ArrayList<>();

		public void addExtension(String extension)
		{
			if(!extension.startsWith("."))
			{
				extension="."+extension;
				extensions.add(extension.toLowerCase());
			}
		}
		public void setDescription(String aDescription)
		{
			description=aDescription;
		}
		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			if(f.isDirectory()) return true;
			
			String name=f.getName().toLowerCase();
			for(String extension:extensions)
			{
				if(name.endsWith(extension))
				{
					return true;
				}
			}
			return false;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return description;
		}
		
	}
	class FileIconViewer extends FileView
	{
		private FileFilter filter;
		public FileIconViewer(FileFilter filter)
		{
			this.filter=filter;
		}
		public Icon getIcon(File f)
		{
			if(!f.isDirectory()&&filter.accept(f))
			{
				return new ImageIcon("ico/pict.png");
				
			}
			else if(f.isDirectory())
			{
				File[]fList=File.listRoots();
				for(File tmp:fList)
				{
					if(tmp.equals(f))
					{
						return new ImageIcon("ico/dsk.png");
						
					}
				}
				return new ImageIcon("ico/folder.jpg");
			}
			return null;
			
		}
	}
	public static void main(String[] args) {
		new ImageViewer().init();
	}
}
