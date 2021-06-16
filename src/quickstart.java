import javax.swing.*;

public class quickstart
{
    public static void main ( final String[] args )
    {
        // You should always work with UI inside Event Dispatch Thread (EDT)
        // That includes installing L&F, creating any Swing components etc.
        SwingUtilities.invokeLater (new Runnable ()
        {
            @Override
            public void run ()
            {
                // Install WebLaF as application LaF
//                WebLookAndFeel.install ();

                // You can also specify preferred skin right-away
                // WebLookAndFeel.install ( WebDarkSkin.class );

                // You can also do that in one of the old-fashioned ways
                // UIManager.setLookAndFeel ( new WebLookAndFeel () );
                // UIManager.setLookAndFeel ( "com.alee.laf.WebLookAndFeel" );
                // UIManager.setLookAndFeel ( WebLookAndFeel.class.getCanonicalName () );

                // You can also configure other WebLaF managers as you like now
                // StyleManager
                // SettingsManager
                // LanguageManager
                // ...

                // Initialize your application once you're done setting everything up
                // JFrame frame = ...

                // You can also use Web* components to get access to some extended WebLaF features
                // WebFrame frame = ...
            }
        } );
    }
}
