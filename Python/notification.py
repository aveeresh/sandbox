import time
from plyer import notification

if __name__=="__main__":
    Count = 1
    while True:
        notification.notify( title = "HEADING HERE",
                             message = " DESCRIPTION HERE",
                             timeout = 2
                           )
        # waiting time
        time.sleep(10)
        print(Count)
        Count = Count + 1
