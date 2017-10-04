import random

def main():
    pass
    # Create 30 patient strings, write them out to a csv file
    p_list = list()

    for p in range(1, 301):
        temp, h_rate, r_rate = randoTemp(), randoHRate(), randoRRate()
        s = str("{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}\n".format(temp, h_rate, r_rate, randoBinary(), randoPCount(), randoBinary(), randoBinary(), randoAge(), randoBinary(), randoBinary(), diagnose(temp, h_rate, r_rate)))
        p_list.append(s)

    print(p_list[0])
    with open("TestSepsis.csv", mode='w') as outfile:
        outfile.write("Body Temp,Heart Rate,Respiratory Rate,Urine Output,Platelet Count,Difficulty Breathing,Abnormal Heart Pump,Age,Abdominal Pain,Weakened Immune System,Sepsis")
        for pa in p_list:
            outfile.writelines(pa)  

def randoTemp():
    pass
    while True:
        t = round(random.random() * 106, 1)
        if t < 106 and t > 94:
            return t
        else: continue

def randoHRate():
    pass
    while True:
        t = int(random.random() * 101)
        if t < 101 and t > 61:
            return t
        else: continue

def randoRRate():
    while True:
        t = int(random.random() * 31)
        if t < 31 and t > 9:
            return t
        else: continue

def randoBinary():
    t = random.random()
    if t < 0.25:
        return True
    else:
        return False

def randoPCount():
    while True:
        t = int(random.random() * 451000)
        if t < 451000 and t > 149000:
            return t
        else: continue

def randoAge():
    while True:
        t = int(random.random() * 100)
        if t < 100 and t > 18:
            return t
        else: continue

# Function determines if the patient has sepsis or not from the mayoclinic ranges
def diagnose(b_temp, h_rate, r_rate):
    d_temp = bool()
    d_h_rate = bool()
    d_r_rate = bool()
    count = int()

    if b_temp > 101 or b_temp < 96.8:
        #d_temp = True
        count+=1
    else: d_temp = False

    if h_rate > 90:
        #d_h_rate = True
        count+=1
    else: d_h_rate = False

    if r_rate > 20:
        #d_r_rate = True
        count+=1
    else: d_r_rate = False

    #if d_temp is True and d_h_rate is True and d_r_rate is True:
    if count >= 2:
        return True
    else: return False



if __name__ == '__main__':
    main()
