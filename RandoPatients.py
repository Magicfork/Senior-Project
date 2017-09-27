import random

def main():
    pass
    # Create 30 patient strings, write them out to a csv file
    p_list = list()

    for p in range(3, 31):
        s = str("{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}\n".format(p, randoTemp(), randoHRate(), randoRRate(), randoBinary(), randoPCount(), randoBinary(), randoBinary(), randoAge(), randoBinary(), 0))
        p_list.append(s)

    print(p_list[0])
    with open("TestSepsis.csv", mode='w') as outfile:
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

class Patient:
    def __init__(self, p_num, b_temp, h_rate, r_rate, u_output, p_count, d_breathing, a_h_pump, age, w_immune, sepsis):
        self.p_num = p_num
        self.b_temp = b_temp
        self.h_rate = h_rate
        self.r_rate = r_rate
        self.u_output = u_output
        self.p_count = p_count
        self.d_breathing = d_breathing
        self.a_h_pump = a_h_pump
        self.age = age
        self.w_immune = w_immune
        self.sepsis = sepsis

    # Represent the object as a line in a .csv file
    def __repr__(self):
        return "{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}\n".format(self.p_num, self.b_temp, self.h_rate, self.r_rate, self.u_output, 
    self.p_count, self.d_breathing, self.a_h_pump, self.age, self.w_immune, self.sepsis)

    # Function determines if the patient has sepsis or not from the mayoclinic ranges
    # def diagnose(self, patient):
    #     if patient.b_temp > 101 or patient.b_temp < 96.8:
    #         pass



if __name__ == '__main__':
    main()
