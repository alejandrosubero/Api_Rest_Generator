import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAtributoComponent } from './create-atributo.component';

describe('CreateAtributoComponent', () => {
  let component: CreateAtributoComponent;
  let fixture: ComponentFixture<CreateAtributoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateAtributoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateAtributoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
