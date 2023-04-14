/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage Flo2.jpeg
 *
 *  @author:
 *
 *************************************************************************/

import java.awt.Color;


public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;

    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename) {
        this.collageDimension =4;
        this.tileDimension= 100;
        this.original = new Picture(filename);
        
        this.collage = new Picture(400,400);

        for (int i =0; i< 400; i++ )
        {
            for (int j = 0; j<400; j++)
            {
                int x = i * original.width()/400;
                int y = j * original.height()/400;
                Color color = original.get(x,y);
                collage.set(i, j,color);     
            }
            //collage.show();
        }
	    // WRITE YOUR CODE HERE**/
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename, int td, int cd) {
        this.collageDimension =cd;
        this.tileDimension= td;
        this.original = new Picture(filename);
        this.collage = new Picture((td*cd),td*cd);

        for (int i =0; i< td*cd; i++ )
        {
            for (int j = 0; j<td*cd; j++)
            {
                int x = i * original.width()/(td*cd);
                int y = j * original.height()/(td*cd);
                Color color = original.get(x,y);
                collage.set(i, j,color);     
            }
            //collage.show();
        }
	    // WRITE YOUR CODE HERE
    }
    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {
        return collageDimension;
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {
        return tileDimension;
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {
        return original;
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {
        return collage;
	    // WRITE YOUR CODE HERE
    }
    
    /*
     * Display the original image
     * Assumes that original has been initialized
     */
    public void showOriginalPicture() {
        original.show();
	    // WRITE YOUR CODE HERE
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() {
        collage.show();
	    // WRITE YOUR CODE HERE
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {
        original= new Picture(filename);
            for (int i =0; i< getTileDimension(); i++ )
            {
                for (int j = 0; j<getTileDimension(); j++)
                {
                    int x = i* original.width()/getTileDimension();
                    int y = j * original.height()/getTileDimension();
                    Color color = original.get(x,y);
                     collage.set(i+collageCol*getTileDimension(),j+collageRow*getTileDimension(),color);  
                }
                //collage.show();
            }           
         
	    // WRITE YOUR CODE HERE
    }
    
    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */
    public void makeCollage () {

        for (int i = 0; i<getCollageDimension(); i ++)
        {
            for (int j = 0; j<getCollageDimension(); j ++)
            {
                for (int t =0; t< getTileDimension(); t++ )
                {
                    for (int p = 0; p<getTileDimension(); p++)
                    {
                        int x = t* original.width()/getTileDimension();
                        int y = p * original.height()/getTileDimension();
                        Color color = original.get(x,y);
                         collage.set(t+(i*getTileDimension()),p+(j*getTileDimension()),color);  
                    }
                    //collage.show();
                }     
            }
            //collage.show();
        }
           
        
	    // WRITE YOUR CODE HERE
    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see CS111 Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) {
          
        if (component.equals("red"))
        {
            for (int i =0; i< getTileDimension(); i++ )
            {
                for (int j = 0; j<getTileDimension(); j++)
                {
                    Color color= collage.get(i+(collageCol*getTileDimension()),j+(collageRow*getTileDimension()));
                    int r= color.getRed();
                    int b = color.getBlue();
                    int g = color.getGreen();


                     collage.set(i+collageCol*getTileDimension(),j+collageRow*getTileDimension(),new Color(r,0,0));  
                }
                //collage.show();
            }           
         

        }
        if(component.equals("blue"))
        {
            for (int i =0; i< getTileDimension(); i++ )
            {
                for (int j = 0; j<getTileDimension(); j++)
                {
                    
                    Color color= collage.get(i+(collageCol*getTileDimension()),j+(collageRow*getTileDimension()));
                    int r= color.getRed();
                    int b = color.getBlue();
                    int g = color.getGreen();


                     collage.set(i+collageCol*getTileDimension(),j+collageRow*getTileDimension(),new Color(0,0,b));  
                }
                //collage.show();
            }           
         
        }
        if (component.equals("green"))
        {
            for (int i =0; i< getTileDimension(); i++ )
            {
                for (int j = 0; j<getTileDimension(); j++)
                {
                
                    Color color= collage.get(i+(collageCol*getTileDimension()),j+(collageRow*getTileDimension()));
                    int r= color.getRed();
                    int b = color.getBlue();
                    int g = color.getGreen();


                     collage.set(i+collageCol*getTileDimension(),j+collageRow*getTileDimension(),new Color(0,g,0));  
                }
                //collage.show();
            }           
         
        }
	    // WRITE YOUR CODE HERE
    }

    /*
     * Grayscale tile at (collageCol, collageRow)
     * (see CS111 Week 9 slides, the code for luminance is at the book's website)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */

    public void grayscaleTile (int collageCol, int collageRow) {
        /*for (int i=0; i<getTileDimension();i++)
        {
            for(int j=0;j<getTileDimension();j++)
            {
                Color color= collage.get(i,j);
                Color gray= Luminance.toGray(color);
                collage.set(i, j, gray);
                

            }
        }*/
        for (int i =0; i< getTileDimension(); i++ )
        {
            for (int j = 0; j<getTileDimension(); j++)
            {
                Color color= collage.get(i+(collageCol*getTileDimension()),j+(collageRow*getTileDimension()));
                Color gray= Luminance.toGray(color);
                 collage.set(i+(collageCol*getTileDimension()),j+(collageRow*getTileDimension()),gray);
            }
            //collage.show();
        }           
     
	    // WRITE YOUR CODE HERE
    }


    /*
     *
     *  Test client: use the examples given on the assignment description to test your ArtCollage
     */
    public static void main (String[] args) {

        ArtCollage art = new ArtCollage(args[0]); 
        art.showCollagePicture();
        
/*
            art.replaceTile("Ariel.jpg", 0,0);
            art.replaceTile("Ariel.jpg", 0,3);
            art.replaceTile("Ariel.jpg", 3,0);
            art.replaceTile("Ariel.jpg", 3,3);
            art.replaceTile("Baloo.jpeg", 0,1);
            art.replaceTile("Baloo.jpeg", 0,2);
            art.replaceTile("PlocLilo.jpg", 1,3);
            art.replaceTile("PlocLilo.jpg", 2,3);
            art.replaceTile("Flo.jpg", 1,0);
            art.replaceTile("Flo.jpg", 2,0);
            art.replaceTile("Baloo.jpeg", 3,1);
            art.replaceTile("Baloo.jpeg", 3,2);

            tra.replaceTile("Baloo.jpeg", 0,0);
            tra.replaceTile("Flo.jpg", 0,2);
            tra.replaceTile("PlocLilo.jpg", 1,1);
            tra.replaceTile("Flo.jpg", 2,0);
            tra.replaceTile("Baloo.jpeg", 2,2);
            tra.replaceTile("Flo2.jpeg", 1,0);
            tra.replaceTile("Flo2.jpeg", 1,2);

            art.grayscaleTile(0,0);
            art.grayscaleTile(1,1);
            art.grayscaleTile(2,0);
            
            tra.grayscaleTile(1,0);

            art.colorizeTile("green", 1, 1);
            art.colorizeTile("red", 1, 2);
            art.colorizeTile("blue", 2, 1);
            art.colorizeTile("green", 2, 2);

            tra.colorizeTile("red", 0, 1);
            tra.colorizeTile("green", 2, 1);
            tra.colorizeTile("blue", 1, 2);

        art.showCollagePicture();
        tra.showCollagePicture();*/
        
    }
}
