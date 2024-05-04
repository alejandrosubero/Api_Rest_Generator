import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSubTaskComponent } from './add-sub-task.component';

describe('AddSubTaskComponent', () => {
  let component: AddSubTaskComponent;
  let fixture: ComponentFixture<AddSubTaskComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddSubTaskComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddSubTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
