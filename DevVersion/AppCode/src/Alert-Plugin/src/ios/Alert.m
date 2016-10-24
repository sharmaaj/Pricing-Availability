#import "Alert.h"

@implementation Alert
- (void)pluginInitialize
{
}

- (void)alert:(CDVInvokedUrlCommand*)command
{
  NSString* callbackId = command.callbackId;
  NSString* title = [command argumentAtIndex:0];
  NSString* message = [command argumentAtIndex:1];
  NSString* button = [command argumentAtIndex:2];

  MyAlertView *alert = [[MyAlertView alloc]
                        initWithTitle:title
                        message:message
                        delegate:self
                        cancelButtonTitle:button
                        otherButtonTitles:nil];
                        alert.callbackId = callbackId;
  [alert show];
}

- (void)alertView:(UIAlertView*)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
  MyAlertView* myAlertView = (MyAlertView*)alertView;
  CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK 
                             messageAsInt:0];
  [self.commandDelegate sendPluginResult:result callbackId:myAlertView.callbackId];
}
@end

@implementation MyAlertView
@synthesize callbackId;
@end